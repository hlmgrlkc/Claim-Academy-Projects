package Servlets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetImage
 */
@WebServlet("/GetImage")
public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String path ="D:\\users\\Desktop\\Java\\Car_Project\\WebContent\\images\\"; 
	BufferedImage img = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		/*
		 * This servlet for getting local Image writing image to html and response image
		 */		
		
		
		HttpSession session = request.getSession(true);

		String fileImgName = request.getParameter("imgName");
		String contentType = this.getServletContext().getMimeType(fileImgName);
		File file = new File(path + fileImgName);
		//ImageIO.setUseCache(true);

		/*
		 * try { // img = ImageIO.read(file); } catch (IOException e) { }
		 */
		
		 response.setHeader("Content-Type", contentType);
	        
         //response.setHeader("Content-Length", String.valueOf(person.getImageData().length));
		 response.setHeader("Content-Length", String.valueOf(file.length()));

         response.setHeader("Content-Disposition", "inline; filename=\"" + fileImgName + "\"");
 		OutputStream out = response.getOutputStream();
 		
 		//This is getting extension
 		String regex = "(?<=\\.).*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileImgName);
		String output = "";
		if (matcher.find()){
		    output = matcher.group();

		}
 		
		//ImageIO.write(img, output , out);
		
		//Responding image to html
		FileInputStream in = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = in.read(buf)) >= 0)
		{
		out.write(buf, 0, len);
		}
		in.close();
		out.close();

		
         //response.getOutputStream().write(out.);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
