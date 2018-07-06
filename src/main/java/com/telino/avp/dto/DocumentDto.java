package com.telino.avp.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * Bean type DTO du Document léger utilisé pour le transfert des méta-données
 * afin de l'utilisation par les calculs 
 *  
 * @author Jiliang.WANG
 *
 */
public class DocumentDto implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8180020104338986201L;
	
	private UUID docid;
	private String title;
	private String archiveDate;
	private Boolean cryptage;
    private String cryptageAlgo;
    private byte[] secretKey;
    private byte[] initVector;
    private String hash;
	private String empreinteSimple;
    private String empreinteUnique;
    private String empreinteTelino;
	
    public DocumentDto() {
		super();
	}

	public UUID getDocid() {
		return docid;
	}

	public void setDocid(UUID docid) {
		this.docid = docid;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArchiveDateMs() {
		return archiveDate;
	}

	public void setArchiveDateMs(String archiveDate) {
		this.archiveDate = archiveDate;
	}

	public Boolean getCryptage() {
		return cryptage;
	}

	public void setCryptage(Boolean cryptage) {
		this.cryptage = cryptage;
	}

	public String getCryptageAlgo() {
		return cryptageAlgo;
	}

	public void setCryptageAlgo(String cryptageAlgo) {
		this.cryptageAlgo = cryptageAlgo;
	}

	public byte[] getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(byte[] secretKey) {
		this.secretKey = secretKey;
	}

	public byte[] getInitVector() {
		return initVector;
	}

	public void setInitVector(byte[] initVector) {
		this.initVector = initVector;
	}
	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getEmpreinteSimple() {
		return empreinteSimple;
	}

	public void setEmpreinteSimple(String empreinteSimple) {
		this.empreinteSimple = empreinteSimple;
	}

	public String getEmpreinteUnique() {
		return empreinteUnique;
	}

	public void setEmpreinteUnique(String empreinteUnique) {
		this.empreinteUnique = empreinteUnique;
	}

	public String getEmpreinteTelino() {
		return empreinteTelino;
	}

	public void setEmpreinteTelino(String empreinteTelino) {
		this.empreinteTelino = empreinteTelino;
	}
	
	@Override
	public String toString() {
		return "Document [docid=" + docid + ", title=" + title + "] \n";
	}	
}
