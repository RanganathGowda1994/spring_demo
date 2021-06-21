package com.cepheid.cloud.skel.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Description extends AbstractEntity {

	@Column
	private String descriptionText;

	@Column
	private String descriptionCategory;

	public Description() {

	}

	public Description(String descriptionText, String descriptionCategory) {
		this.descriptionText = descriptionText;
		this.descriptionCategory = descriptionCategory;
	}

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	public String getDescriptionCategory() {
		return descriptionCategory;
	}

	public void setDescriptionCategory(String descriptionCategory) {
		this.descriptionCategory = descriptionCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descriptionCategory == null) ? 0 : descriptionCategory.hashCode());
		result = prime * result + ((descriptionText == null) ? 0 : descriptionText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Description other = (Description) obj;
		if (descriptionCategory == null) {
			if (other.descriptionCategory != null)
				return false;
		} else if (!descriptionCategory.equals(other.descriptionCategory))
			return false;
		if (descriptionText == null) {
			if (other.descriptionText != null)
				return false;
		} else if (!descriptionText.equals(other.descriptionText))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Description [descriptionText=" + descriptionText + ", descriptionCategory=" + descriptionCategory + "]";
	}

}
