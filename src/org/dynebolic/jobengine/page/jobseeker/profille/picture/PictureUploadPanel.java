package org.dynebolic.jobengine.page.jobseeker.profille.picture;

import java.io.File;
import java.io.IOException;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.lang.Bytes;
import org.dynebolic.jobengine.JobEngineApplication;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.jobseeker.JobSeekerPage;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.library.PictureViewPanel;

public class PictureUploadPanel extends BasePanel {
	
	private FileUploadField file;
	private JobSeekerService service = new JobSeekerService();
	public PictureUploadPanel(String id,final JobSeekerPage page) {
		super(id);
		
		FeedbackPanel feedback = new FeedbackPanel("feedback");
		add(feedback);
		
		Form form = new Form("formUpload"){
			protected void onSubmit() {
				FileUpload upload = file.getFileUpload();
				try {
					JobSeeker js = getJESession().getUser().getJobSeeker();
					String js_id = js.getId().toString();
					String photo = getUploadPath() + "jobseeker_" + js_id;
					
					upload.writeTo(new File(photo));
					js.setPhoto(photo);
					service.save(js);
					getJESession().getUser().getJobSeeker().setPhoto(photo);
					PictureViewPanel photoPanel = new PictureViewPanel("photo",getJESession().getUser().getJobSeeker().getPhoto(), 210, 210);
					photoPanel.setOutputMarkupId(true);
					page.addOrReplace(photoPanel);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		add(form);
		form.setMultiPart(true);
		form.setMaxSize(Bytes.kilobytes(200));
		file = new FileUploadField("file");
		form.add(file);
		form.add(new UploadProgressBar("progress", form));
	}

}
