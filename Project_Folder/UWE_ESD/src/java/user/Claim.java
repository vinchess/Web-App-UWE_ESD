package user;

/**
 *
 * @author Jayson
 */
public class Claim
{
  private String m_name;

  private String m_claimType;

  private Long  m_claimNumber;
  
  User user = new User();
  
  public String getClaimType()
  {
    return m_claimType;
  }

  public Long getClaimNumber()
  {
    return m_claimNumber;
  }
}


    

