package com.yedam.app.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	// 여기서 업로드진행
	@Value("${file.upload.path}")
	// ==application.properties의 key값 // 이 key명은 환경변수라서 이름 내 마음대로 지정해도 됨ㅇㅇ
	public String uploadPath;

//	@GetMapping("getUploadPath")
//	@ResponseBody
//	public String getUploadPath() {
//		return uploadPath;
//	}
	
	@GetMapping("upload")
	public void getUpladPat() {
	}

	@PostMapping("/uploadsAjax")
	@ResponseBody
	public void uploadFile(@RequestPart MultipartFile[] uploadFiles) {
	//						첨부파일처리할때사용하는어노테이션으로 사용자가 보내온 멀티미디어파일 받아옴
	//						해당 어노테이션은 스프링이 제공함
	//						주로 배열 사용함 cuz 동시에 여러개의 미디어파일을 줄 수 있다고 상정
		for (MultipartFile uploadFile : uploadFiles) {
			//사용자가보내온파일의정보를가지고있음 MultipartFile 이름, originalFileName, contentType, size, bytes
			if (uploadFile.getContentType().startsWith("image") == false) {
				System.err.println("this file is not image type");
				return ;
			} // 해당은 선택인데 멀티파트는 이미지에만 한정xxx 동영상, 음성 여러가지 있을 수 있음
			  // 이건 이미지만 선택하겠다는 뜻임ㅇㅇ

			String originalName = uploadFile.getOriginalFilename();
			// 1차적으로 사용자가 보내온 정보 확인
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
			// 그 중에서 불필요한 정보 잘라내는 작업

			System.out.println("fileName : " + fileName);

			// 날짜 폴더 생성
			String folderPath = makeFolder();
			// UUID
			String uuid = UUID.randomUUID().toString();
			// 저장할 파일 이름 중간에 "_"를 이용하여 구분
			// 랜덤한 아무 값 만들어냄?
			// 상기 두 줄이 결코 겹치지 않는 파일명 만듦. => randomUUID()가 랜덤한 값 만들어냄ㅇㅇ
			String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;
			String saveName = uploadPath + File.separator + uploadFileName;
			// 사용자가 보내온 파일명 그대로 사용한다면 상기 3줄 필요xx
			// but 사용자가 많다면 이름충돌이 너무 많음ㅇㅇ
			// 그래서 그걸 방지하는 거임ㅇㅇ
			
			Path savePath = Paths.get(saveName); // 물리적 위치에대한 정보를 가지고있음
			// Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
			System.out.println("path : " + saveName);
			try {
				uploadFile.transferTo(savePath); // 업로드 
				// uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
				// 가장 핵심적인 부분이랬음ㅇㅇ
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//DB에 해당 경로 저장
			String imagePath = uploadFileName.replace(File.separator, "/");
			//cuz 경로를 나누는 것들이 \이거임ㅇㅇ 이건 운영체제에서 사용하는 거임ㅇㅇ
			//==> imagePath를 DB에 저장해야 됨ㅇㅇ
			// 1) 사용자가 업로드 할 때 사용한 파일명
			// 2) 실제 서버에 업로드 할 때 사용한 경로
			// 두 개 다 저장해야 됨ㅇㅇ cuz 사용자는 파일명으로 인식, but 내부에서는 유니크한 값으로 인식
			// 실제 파일명, 서버내에 있는 위치 함께 움직이며 DB에 저장함ㅇㅇ
			System.out.println("uplaodfilename : " + uploadFileName);
			System.out.println("imagepath : " + imagePath);
		
			// saveName ==

		}
	}

	private String makeFolder() {

		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);

		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	/*
	 * private String setImagePath(String uploadFileName) { return
	 * uploadFileName.replace(File.separator, "/"); }
	 */
}
