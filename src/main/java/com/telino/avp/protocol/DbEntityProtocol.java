package com.telino.avp.protocol;

/**
 * Les constants utilise dans les DB entities 
 * 
 * @author Jiliang.WANG
 *
 */
public final class DbEntityProtocol {
	private DbEntityProtocol() {
		throw new AssertionError("Instantiation not allowed!");
	}
	
	/**
	 * Constant de commande
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum BackgroundService {
		CREATELOGARCHIVE(" "), 
		CREATELOGEVENT(" "),
		DESTROY(" "),
		CHECKFILES("Contrôle de l'intégralité des archives par le module de stockage "),
		IMPORTFILES("Importe des documents avec aumtomateRC module ");
		
		private String detail;

		private BackgroundService(String detail) {
			this.detail = detail;
		}

		public String getDetail() {
			return this.detail;
		}
	}

	
	/**
	 * 
	 * Etat d'exploitation de tache
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum ExpTaskState {
		I, E, A, T;
	}
	
	/**
	 * Etat d'exploitation dans log_event
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum LogEventState {
		I, T;
	}

	/**
	 * Type code dans log_archive
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum LogArchiveType {
		A("Actions sur les archives "), 
		C("Contrôle d'intégrité de l'archive "), 
		S("Scellement des journaux "), 
		P("Action sur les profils "),
		L("Lecture");

		private String detail;

		private LogArchiveType(String detail) {
			this.detail = detail;
		}

		public String getDetail() {
			return this.detail;
		}
	}
	
	/**
	 * Type code dans log_event
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum LogEventType {
		C("Contrôle d'intégrité de l'archive "), 
		S("Scellement "), 
		E("Apllication ");

		private String detail;

		private LogEventType(String detail) {
			this.detail = detail;
		}

		public String getDetail() {
			return this.detail;
		}
	}
	
	
	/**
	 * Type code Task Exploitation
	 * 
	 * @author jwang
	 *
	 */
	public static enum ExpTaskType {
		
		NEED_HUMAN_INTERVENTION(1L),
		
		RELAUNCH_FILE_ENTIRETY_CHECK(2L),

		CHECK_RESTORE_MASTER_HASH(3L),
		CHECK_RESTORE_MIRROR_HASH(4L),
		RESTORE_MASTER_FILE(5L),
		RESTORE_MIRROR_FILE(6L),
		CHECK_RESTORE_MASTER_METADATA(7L),
		CHECK_RESTORE_MIRROR_METADATA(8L);
		
		private Long typeId;
		
		
		private ExpTaskType(Long typeId) {
			this.typeId = typeId;
		}
		
		public static ExpTaskType getType(Long id) {
		      
	        if (id == null) {
	            return null;
	        }

	        for (ExpTaskType taskType : ExpTaskType.values()) {
	            if (id.equals(taskType.getTypeId())) {
	                return taskType;
	            }
	        }
	        throw new IllegalArgumentException("No matching type for id " + id);
	    }
		
		public Long getTypeId() {
			return typeId;
		}
	}
	
	/**
	 * Constant de code detail de contrôler de fichier
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum LogEventDetail {
		HASH_NOT_MATCH_ERROR_IN_MASTER, 
		HASH_NOT_MATCH_ERROR_IN_MIRROR, 
		NOT_FOUND_ERROR_IN_MASTER, 
		NOT_FOUND_ERROR_IN_MIRROR, 
		DECRYPT_ERROR_IN_MASTER, 
		DECRYPT_ERROR_IN_MIRROR, 
		SHA_HASH_ERROR_IN_MASTER,
		SHA_HASH_ERROR_IN_MIRROR,
		ENTIRETY_ERROR_IN_MASTER,
		ENTIRETY_ERROR_IN_MIRROR;
	}
	
	
	public static enum CommunicationState {
		V, R, E;
	}
	
	public static enum RestitutionState {
		V, E;
	}
	
	
	/**
	 * Type status dans document
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum DocumentStatut {
		REARDY_FOR_ARCHIVE(0), 
		ARCHIVED(1), 
		ATTESTATION(2);

		private int statutCode;

		private DocumentStatut(int statutCode) {
			this.statutCode = statutCode;
		}

		public int getStatutCode() {
			return this.statutCode;
		}
	}
	
	/**
	 * Status dans draft 
	 * 
	 * @author jwang
	 *
	 */
	public static enum DraftStatut {
		ARCHIVED("1-Archivé"), 
		REFUSED("3-Refusé"),
		TRANSMIS("4-Transmis"),
		ARCHIVING("5-En cours d archivage");

		private String detail;

		private DraftStatut(String detail) {
			this.detail = detail;
		}
		
		@Override
		public String toString() {
			return this.detail;
		}
	}	
}
