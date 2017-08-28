package com.pdamjanovic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.servlet.http.Part;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class BookFile extends AbstractEntity {

	@Column
	private String fileName;

	@Column
	private String mime;

	@Column
	private String storedFileName;

	@Transient
	@Field(type = FieldType.String, analyzer = "sr_analyzer")
	private String content;

	public BookFile() {
	}

	public BookFile(MultipartFile file) {
		this.fileName = file.getOriginalFilename();
		this.mime = file.getContentType();
	}

	public BookFile(Part file) {
		this.fileName = file.getSubmittedFileName();
		this.mime = file.getContentType();
	}

	public BookFile(Long id, String fileName, String mime, String storedFileName) {
		this.setId(id);
		this.fileName = fileName;
		this.mime = mime;
		this.storedFileName = storedFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getStoredFileName() {
		return storedFileName;
	}

	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BookFile{" + "id='" + super.getId() + '\'' + ", fileName='" + fileName + '\'' + ", mime='" + mime + '\''
				+ '}';
	}
}
