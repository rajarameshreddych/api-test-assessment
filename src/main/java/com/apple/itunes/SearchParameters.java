package com.apple.itunes;

public class SearchParameters {
	private String term;
    private String country;
    private String media;
    private String entity;
    private String attribute;
    private String callback;
    private String limit;
    private String lang;
    private String version;
    private String explicit;

    public SearchParameters() {}
    
    public SearchParameters(String term, String country, String media, String entity, String attribute, String callback,
    		String limit, String lang, String version, String explicit) {
        this.term = term;
        this.country = country;
        this.media = media;
        this.entity = entity;
        this.attribute = attribute;
        this.callback = callback;
        this.limit = limit;
        this.lang = lang;
        this.version = version;
        this.explicit = explicit;
    }
    
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getExplicit() {
		return explicit;
	}

	public void setExplicit(String explicit) {
		this.explicit = explicit;
	}
	
}
