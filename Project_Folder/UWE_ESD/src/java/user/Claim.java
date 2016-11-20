package user;

/**
 *
 * @author jayson
 */
public class Claim {
    private String dor;
    private int max_claim;
    private double claim_amount;
    
    private String mem_id;
    private String date;
    private String rationale;
    private String status;
    private String amount;
   
    
    public Claim(){}
    public Claim(String status,double balance,int max_claim){
        
    }
    public Claim(String mem_id, String date, String rationale, String status, String amount){
        this.mem_id = mem_id;
        this.date = date;
        this.rationale = rationale;
        this.status = status;
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDor() {
        return dor;
    }

    public void setClaim_amount(double claim_amount) {
        this.claim_amount=claim_amount;
    }
    
    public double getClaim_amount() {
        return claim_amount;
    }

    public void setMax_claim(int max_claim) {
        this.max_claim=max_claim;
    }
    
    public int getMax_claim() {
        return max_claim;
    }

    /**
     * @return the mem_id
     */
    public String getMem_id() {
        return mem_id;
    }

    /**
     * @param mem_id the mem_id to set
     */
    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the rationale
     */
    public String getRationale() {
        return rationale;
    }

    /**
     * @param rationale the rationale to set
     */
    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

}
