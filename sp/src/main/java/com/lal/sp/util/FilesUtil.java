package com.lal.sp.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FilesUtil {

	public static String saveFile(MultipartFile multipartFile, String realPath, String dirName) {
		File dir = new File(realPath + dirName);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String suffix = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		String newName = new Date().getTime() + "." + suffix;
		try {
			multipartFile.transferTo(new File(dir,newName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dirName+newName;
	}

}
