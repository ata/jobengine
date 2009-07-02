package org.dynebolic.jobengine.page.employer.profille.picture;

import java.io.File;
import java.io.IOException;

import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.lang.Bytes;
import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.employer.EmployerPage;
import org.dynebolic.jobengine.service.EmployerService;
import org.dynebolic.library.PictureViewPanel;

@SuppressWarnings("serial")
public class PictureUploadPanel extends BasePanel {
	
	private FileUploadField file;
	private EmployerService service = new EmployerService();
	public PictureUploadPanel(String id,final EmployerPage page) {
		super(id);
		
		FeedbackPanel feedback = new FeedbackPanel("feedback");
		add(feedback);
		
		Form form = new Form("formUpload"){
			protected void onSubmit() {
				FileUpload upload = file.getFileUpload();
				try {
					Employer emp = getJESession().getUser().getEmployer();
					String emp_id = emp.getId().toString();
					String photo = getUploadPath() + "employer_" + emp_id;
					
					upload.writeTo(new File(photo));
					emp.setPhoto(photo);
					service.save(emp);
					getJESession().getUser().getEmployer().setPhoto(photo);
					PictureViewPanel photoPanel = new PictureViewPanel("photo",getJESession().getUser().getEmployer().getPhoto(), 210, 210);
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
