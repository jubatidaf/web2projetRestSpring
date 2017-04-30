package net.sepa.SepaTransImplementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import net.sepa.SepaTransModel.DebtorAccountClass;
import net.sepa.SepaTransModel.DebtorAgentClass;
import net.sepa.SepaTransModel.DebtorClass;
import net.sepa.SepaTransModel.DirectDebitTransactionClass;
import net.sepa.SepaTransModel.DirectDebitTransactionInformationClass;
import net.sepa.SepaTransModel.DirectDebitTransactionInformationResumeClass;
import net.sepa.SepaTransModel.FinalInstitutionIdentifierClass;
import net.sepa.SepaTransModel.IdentificationClass;
import net.sepa.SepaTransModel.MandateRelatedInformationClass;
import net.sepa.SepaTransModel.TransactionStatistiqueClass;

public class TransactionImpl implements InterfaceTransaction{
	
	private JdbcTemplate jdbcTemplate;
	
	public TransactionImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
	//ajouter une transaction 
	@Override
	public void ajouter_transaction(DirectDebitTransactionInformationClass DrctDbtTxInf) {
		// insert
		String sql = "INSERT INTO sepatransactiontable (numero,paieid, instdamt, "
				+ "mandatid, datetrans, bic, nom, iban, relateinf) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				
				
				jdbcTemplate.update(sql, DrctDbtTxInf.getNum(), DrctDbtTxInf.getPmtId(), DrctDbtTxInf.getInstdAmt(), 
						DrctDbtTxInf.getDrctDbtTx().getMndtRltdInf().getMndtId(), 
						DrctDbtTxInf.getDrctDbtTx().getMndtRltdInf().getDtOfSgntr()
						, DrctDbtTxInf.getDbtrAgt().getFinInstnId().getBIC(), DrctDbtTxInf.getDbtr().getNm(), 
						DrctDbtTxInf.getDbtrAcct().getId().getIBAN(), DrctDbtTxInf.getRmtInf());
	}

	// trouvé une transaction par son "Payment Identification"
	@Override
	public DirectDebitTransactionInformationClass get_transaction_by_id(String pmtId) {
		String sql = "SELECT * FROM sepatransactiontable WHERE paieid='" + pmtId +"'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<DirectDebitTransactionInformationClass>() {

			public DirectDebitTransactionInformationClass extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				
				
				
				if (rs.next()) {
					//recuperer les information pour une transaction donné
					
					MandateRelatedInformationClass mandateRelatedInformationClass=new MandateRelatedInformationClass(rs.getString("mandatid"),
							rs.getString("datetrans"));
					
					DirectDebitTransactionClass debitTransactionClass= new DirectDebitTransactionClass((
							mandateRelatedInformationClass));
					/*"INSERT INTO sepatransactiontable (numero,paieid, instdamt, "
					+ "mandatid, datetrans, bic, nom, iban, relateinf) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"*/
					FinalInstitutionIdentifierClass finalInstitutionIdentifierClass=new FinalInstitutionIdentifierClass(rs.getString("bic"));
					DebtorAgentClass debtorAgentClass=new DebtorAgentClass(finalInstitutionIdentifierClass);
					DebtorClass debtorClass=new DebtorClass(rs.getString("nom"));
					IdentificationClass identificationClass=new IdentificationClass(rs.getString("iban"));
					DebtorAccountClass debtorAccountClass=new DebtorAccountClass(identificationClass);
					
					//retourné les information de la transaction recherché 
					return new DirectDebitTransactionInformationClass(rs.getInt("id_sepa_transaction"),
							rs.getString("numero"),rs.getString("paieid"),rs.getDouble("instdamt"), 
								debitTransactionClass,
							debtorAgentClass,debtorClass, 
							debtorAccountClass,rs.getString("relateinf"));
				}
				
				return null;
			}
			
		});
	}
	
	//trouvé la valeur maximal de "transaction_id" dans l'ensemble des transaction
	@Override
	public int get_Max_Id() {
		return jdbcTemplate.queryForObject("SELECT MAX(id_sepa_transaction) FROM sepatransactiontable", Integer.class);
	}

	// lister toutes les transaction avec leurs informations détaillée
	@Override
	public List<DirectDebitTransactionInformationClass> list_tous_transactions(){
		
		String sql = "SELECT * FROM sepatransactiontable";
		List<DirectDebitTransactionInformationClass> listTransactions = jdbcTemplate.query(sql, new RowMapper<DirectDebitTransactionInformationClass>() {

			public DirectDebitTransactionInformationClass mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				MandateRelatedInformationClass mandateRelatedInformationClass=new MandateRelatedInformationClass(rs.getString("mandatid"),
						rs.getString("datetrans"));
				
				DirectDebitTransactionClass debitTransactionClass= new DirectDebitTransactionClass((
						mandateRelatedInformationClass));
				
				FinalInstitutionIdentifierClass finalInstitutionIdentifierClass=new FinalInstitutionIdentifierClass(rs.getString("BIC"));
				DebtorAgentClass debtorAgentClass=new DebtorAgentClass(finalInstitutionIdentifierClass);
				DebtorClass debtorClass=new DebtorClass(rs.getString("numero"));
				IdentificationClass identificationClass=new IdentificationClass(rs.getString("iban"));
				DebtorAccountClass debtorAccountClass=new DebtorAccountClass(identificationClass);
				
				return new DirectDebitTransactionInformationClass(rs.getInt("id_sepa_transaction"),
						rs.getString("numero"),rs.getString("paieid"),rs.getDouble("instdamt"), 
						debitTransactionClass,
						debtorAgentClass,debtorClass, 
						debtorAccountClass,rs.getString("relateinf"));
			}
			
		});
		
		return listTransactions;
	}
	
	@Override
	public List<DirectDebitTransactionInformationResumeClass> list_Resume_All_transaction() {
		String sql = "SELECT * FROM sepatransactiontable";
		List<DirectDebitTransactionInformationResumeClass> listTransactions = jdbcTemplate.query(sql, new RowMapper<DirectDebitTransactionInformationResumeClass>() {

			public DirectDebitTransactionInformationResumeClass mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new DirectDebitTransactionInformationResumeClass(rs.getString("numero"),rs.getString("paieid"),rs.getDouble("instdamt"), 
						rs.getString("datetrans"));
				
			}
			
		});
		
		return listTransactions;
	}
	/*"INSERT INTO sepatransactiontable (numero,paieid, instdamt, "
	+ "mandatid, datetrans, bic, nom, iban, relateinf) "
	+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"*/
	/*Liste de toutes les transactions résumées*/
	/*Retourne le nombre de transactions et le montant total des transactions dans la base de données*/
	@Override
	public TransactionStatistiqueClass get_Stats_transaction() {
		return new TransactionStatistiqueClass(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM sepatransactiontable", Integer.class),
				jdbcTemplate.queryForObject("SELECT ROUND(SUM(instdamt),2) FROM sepatransactiontable", Double.class));
	}

}
