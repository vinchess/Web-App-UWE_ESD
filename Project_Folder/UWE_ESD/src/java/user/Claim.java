package user;

/**
 *
 * @author jayson
 */
public class Claim {
    private String status;
    private String dor;
    private int max_claim;
    private double claim_amount;
   
    
    public Claim(){}
    public Claim(String status,double balance,int max_claim){
        
    }
    public Claim(String status, String dor,double claim_amount){
        this.status=status;
        this.dor=dor;
        this.claim_amount=claim_amount;
        this.max_claim=max_claim;
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

}
