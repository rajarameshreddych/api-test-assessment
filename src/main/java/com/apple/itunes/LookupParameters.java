package com.apple.itunes;

public class LookupParameters {
	private String id;
	private String amgArtistId;
	private String isbn;

	// We can add more parameters as per API. FOr now I limited to above 3
	
	public LookupParameters() {}
	
	public LookupParameters(String id, String amgArtistId, String isbn) {
		super();
		this.id = id;
		this.amgArtistId = amgArtistId;
		this.isbn = isbn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmgArtistId() {
		return amgArtistId;
	}

	public void setAmgArtistId(String amgArtistId) {
		this.amgArtistId = amgArtistId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
