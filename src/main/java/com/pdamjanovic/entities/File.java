package com.pdamjanovic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class File extends AbstractEntity {

	@Column
	private String fileName;

	@Column
	private String mime;

	public File() {
	}

	public File(Long id, String fileName, String mime) {
		this.setId(id);
		this.fileName = fileName;
		this.mime = mime;
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

}
