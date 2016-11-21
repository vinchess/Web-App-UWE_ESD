package user;

/**
 *
 * @author jayson
 */
public class Claim {
    
    private String mem_id;
    private String date;
    private String rationale;
    private String status;
    private double amount;
   
    
    public Claim(){}
    public Claim(String status,double balance,int max_claim){
        
    }
    public Claim(String mem_id, String date, String rationale, String status, double amount){
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
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

}
