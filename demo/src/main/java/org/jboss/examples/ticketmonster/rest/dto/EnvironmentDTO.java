package org.jboss.examples.ticketmonster.rest.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EnvironmentDTO implements Serializable {
	
	private String text;
	
	public EnvironmentDTO(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
