package org.dynebolic.library;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.BufferedDynamicImageResource;
import org.dynebolic.jobengine.page.BasePanel;

@SuppressWarnings("serial")
public class PictureViewPanel extends BasePanel {

	public PictureViewPanel(String id,final String path, int width, int height) {
		super(id);
		
		BufferedDynamicImageResource resource = new BufferedDynamicImageResource();
        resource.setImage(scale(path, width, height)); 
		
        
        
		add(new Image("image",resource));
	}
	
	public BufferedImage scale(String srcFile, int destWidth, int destHeight) {
		BufferedImage src = null;
		try {
			src = ImageIO.read(new File(srcFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage dest = new BufferedImage(destWidth,destHeight,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = dest.createGraphics();
		
		
		AffineTransform at = AffineTransform.getScaleInstance(
			(double)destWidth/src.getWidth(),
				(double)destHeight/src.getHeight());
		g.drawRenderedImage(src,at);
		return dest;
	}
}
