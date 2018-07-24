package com.telino.avp.protocol;

import com.telino.avp.protocol.DbEntityProtocol.ExpTaskType;

/**
 * Les constants du protocole de communication entre ArchivageServeur et Module
 * Stockage Ils sont regroupés par certaines classes internes
 * 
 * @author Jiliang.WANG
 *
 */
/**
 * @author jwang
 *
 */
public final class AvpProtocol {
	
	public static final String ERROR_IN_PRIN = "_IN_MASTER";
	public static final String ERROR_IN_MIRO = "_IN_MIRROR";
	
	private AvpProtocol() {
		throw new AssertionError("Instantiation not allowed!");
	}

	/**
	 * Constant de commande
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum Commande {
		ARCHIVE("archive", "Archive"),
		CHECK_FILES("checkfiles", "Contrôle d'intégrité des archives"),
		CHECK_LOG_CHECK("checklogcheck", "Contrôle d'intégrité du journal des archives"),
		CHECK_LOG_EVENT("checklogevent", "Contrôle d'intégrité du journal des évènements"),
		CREATE_LOG_CHECK("createlogcheck", "Scellement du journal des archives"),
		CREATE_LOG_EVENT("createlogevent", "Scellement du journal des évènements"),
		CREATE_STORAGE_UNIT("createstorageunit", "Create storage unit"),
		CONTROL("control", "Contrôle d'intégrité d'une archive"),
		COMMUNICATION("communication", "Communication d'une ou plusieurs archives"),
		COMMUNICATION_VALIDATED("communicationvalidated", "Communication validated"),
		DELAY("delay", "Modification du delai de conservation d'une archive"),
		DELETE("delete", "Destruction d'une archive"),
		DELETE_DRAFT("deleteDraft", "Suppression d'une demande d'archivage"),		
		EXP_TASK_NEED_HUMAN_INTERVENTION(ExpTaskType.NEED_HUMAN_INTERVENTION.toString(), ""),		
		EXP_TASK_RELAUNCH_FILE_ENTIRETY_CHECK(ExpTaskType.RELAUNCH_FILE_ENTIRETY_CHECK.toString(), ""),
		EXP_TASK_CHECK_RESTORE_MASTER_HASH(ExpTaskType.CHECK_RESTORE_MASTER_HASH.toString(), ""),
		EXP_TASK_CHECK_RESTORE_MIRROR_HASH(ExpTaskType.CHECK_RESTORE_MIRROR_HASH.toString(), ""),
		EXP_TASK_RESTORE_MASTER_FILE(ExpTaskType.RESTORE_MASTER_FILE.toString(), ""),
		EXP_TASK_RESTORE_MIRROR_FILE(ExpTaskType.RESTORE_MIRROR_FILE.toString(), ""),
		EXP_TASK_CHECK_RESTORE_MASTER_METADATA(ExpTaskType.CHECK_RESTORE_MASTER_METADATA.toString(), ""),
		EXP_TASK_CHECK_RESTORE_MIRROR_METADATA(ExpTaskType.CHECK_RESTORE_MIRROR_METADATA.toString(), ""),		
		GET_ATTESTATION("getattestation", "Récupération du contenu d'une attestation"),
		GET_COMMUNICATION("getcommunication", "Communication d'une ou plusieurs archives"),
		GET_DRAFT_INFO("getDraftInfo", "get Draft Info"),
		GET_DOC("get", "Récupération du contenu d'une archive"), 
		GET_INFO("getinfo", "get info"),
		GET_LOG_FILE("getlogfile", "get log file"),
		GET_RESTITUTION("getrestitution", "get restitution"),
		GET_RIGHTS("getrights", "get rights"),
		GET_USER_PROFILES("getuserprofiles", "get user profiles"),
		IMPORT_FILES("importfiles", "Import automatique des documents à archiver"),
		INIT_STORAGE_UNIT("initstorageunit", ""),
		LIST("list", "list"),
		LOG_EVENT("logevent", "log event"),
		LOG_ARCHIVE("logarchive", "log archive"),
		LOGICAL_DELETE("logicaldelete", "Ajout d'archives à la liste des archives à détruire"),
		READ_DRAFT("readDraft", "Récupération du contenu d'une demande d'archivage"),
		REFUS_COMMUNICATION("refuscommunication", "Refus d'une demande de communication"),
		REFUS_DRAFT("refusDraft", "Refus d'une demande d'archivage"),
		RESTITUTION("restitution", "Restitution d'une ou plusieurs archives"),
		STORE("store", "Archivage d'une archive"),
		STORE_DRAFT("storeDraft", "Création d'une demande d'archivage"),
		STORE_PASSWORD("storepassword", "store password"),
		UPDATE_DRAFT("updateDraft", "Mise à jour d'une demande d'archivage"),
		VALIDE_DRAFT("valideDraft", "validation d'une demande d'archivage"),
		VALIDATION_COMMUNICATION("validationcommunication", "Validation d'une communication"),
		VALIDATION_RESTITUTION("validationrestitution", "Validation d'une restitution");

		private String command;
		private String process;

		private Commande(String val, String process) {
			this.command = val;
			this.process = process;
		}
		
		@Override
		public String toString() {
			return this.command;
		}
		
		public String getProcess() {
			return process;
		}

		public static Commande getEnum(final String val) {
			for (Commande t : values()) {
				if (t.toString().equals(val)) return t;
			}
			throw new IllegalArgumentException();
		}
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
		CHECKFILES("Contrôle de l'intégralité des archives par le module de stockage ");
		
		private String detail;

		private BackgroundService(String detail) {
			this.detail = detail;
		}

		public String getDetail() {
			return this.detail;
		}
	}

	/**
	 * Constant de code de retour
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum ReturnCode {
		OK("Dépôt effectué"), KO(""), ERROR(""), INIT("init");
		
		private String depotMessage;
		
		private ReturnCode(String depotMsg) {
			this.depotMessage = depotMsg;
		}

		public String getDepotMessage() {
			return this.depotMessage;
		}
	}

	/**
	 * Constant de code error de contrôler de fichier
	 * 
	 * @author Jiliang.WANG
	 *
	 */
	public static enum FileReturnError {
		HASH_NOT_MATCH_ERROR("Le hash de log_archive n’égale pas l’empreinte de l’archivage de DB "), 
		NOT_FOUND_ERROR("L’archive n’existe pas dans stockage "), 
		DECRYPT_ERROR("Le décryptage de l’archive est échoué "), 
		SHA_HASH_ERROR("Le hachage de calcul d’empreinte est échoué "),
		ENTIRETY_ERROR("Le contrôle de l’intégralité de l’archive est échoué ");

		private String detail;

		private FileReturnError(String detail) {
			this.detail = detail;
		}

		public String getDetail() {
			return this.detail;
		}
	}
	
	
	
	/**
	 * SedaArchiveAdapter REST API resource localation
	 * 
	 * @author jwang
	 *
	 */
	
	public static final String SEDA_RES = "/seda"; 
	
	public static final String TRANSFER_REQUEST = "/transfer-request";
	public static final String TRANSFER = "/transfer";
	public static final String DELIVERY_REQUEST = "/delivery-request";
	public static final String RESTITUTION_REQUEST = "/restitution-request";
	public static final String RESTITUTION_REQUEST_REPLY = "/restitution-request-reply";
	public static final String AUTHORIZATION_ORIGINATING_AGENCY_REQUEST_REPLY = "/authorization-originating-agency-request-reply";
	public static final String AUTHORIZATION_CONTROL_AUTHORITY_REQUEST_REPLY = "/authorization-control-authority-request-reply";
	
	
	public static final String AVP_RES = "/avp";
	
	public static final String TRANSFER_REQUEST_REPLY = "/transfer-request-reply";
	public static final String TRANSFER_REPLY = "/transfer-reply";
	public static final String DELIVERY_REQUEST_REPLY = "/delivery-request-reply";
	public static final String MODIFICATION_NOTIFICATION = "/modification-notification";
	public static final String DESTRUCTION_NOTIFICATION = "/destruction-notification";
	public static final String RESTITUTION = "/restitution";
	public static final String AUTHORIZATION_ORIGINATING_AGENCY_REQUEST = "/authorization-originating-agency-request";
	public static final String AUTHORIZATION_CONTROL_AUTHORITY_REQUEST = "/authorization-control-authority-request";
		
}
