package com.lal.sp.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 *
 */
public class ImgUtil {
	private String srcFile;
	private String destFile;
	private int width;
	private int height;
	long filesize;
	private Image img;

	/**
	 * 
	 * @param fileName
	 *            :完整文件名称
	 * @throws IOException
	 */
	public ImgUtil(String fileName) throws IOException {
		File _file = new File(fileName); // 读入文件
		filesize = _file.length();
		this.srcFile = _file.getName();
		this.destFile = this.srcFile
				.substring(0, this.srcFile.lastIndexOf(".")) + "_s.jpg";
		img = javax.imageio.ImageIO.read(_file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		//System.out.println("得到源图宽" + width);
		height = img.getHeight(null); // 得到源图长
		//System.out.println("得到源图长" + height);

	}

	/**
	 * 根据已有图片文件生成缩略图文件
	 * 
	 * @param w
	 *            :要形成的缩略图的宽度
	 * @param h
	 *            :要形成的缩略图的高度
	 * @param objFilename
	 *            ：要生成的缩略图文件名
	 * @throws IOException
	 */
	public String resize(int w, int h, String objFilename) throws IOException {
		BufferedImage _image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		_image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		FileOutputStream out = new FileOutputStream(objFilename); // 输出到文件流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(_image); // 近JPEG编码
		out.close();
		return null;
	}

	/**
	 * 根据已有图片文件生成缩略图直接打印到页面
	 * 
	 * @param w
	 *            :要形成的缩略图的宽度
	 * @param h
	 *            :要形成的缩略图的高度
	 * @param response
	 *            :HttpServletResponse对象
	 * @throws IOException
	 */
	public String resize(int w, int h, HttpServletResponse response)
			throws IOException {
		BufferedImage _image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		_image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		OutputStream out = response.getOutputStream();

		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(_image); // 近JPEG编码
		out.close();
		return null;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public static void main(String[] args) throws IOException {
		ImgUtil imgUtil=new ImgUtil("F:\\psb.jpg");
	    imgUtil.resize(150, 100, "F:\\11.jpg");
	}
}
