

/* First created by JCasGen Mon Oct 12 12:04:00 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Annotation indicating precision at a certain rank for the given test element.
 * Updated by JCasGen Mon Oct 19 15:25:59 EDT 2015
 * XML source: /media/maki/OS/Users/Keith/Documents/CMU/Coursework/11791/PI7/pi7-kmaki/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class PAtN extends Metric {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PAtN.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected PAtN() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public PAtN(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public PAtN(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public PAtN(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: n

  /** getter for n - gets The rank at which the precision at n was computed.
   * @generated
   * @return value of the feature 
   */
  public int getN() {
    if (PAtN_Type.featOkTst && ((PAtN_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "type.PAtN");
    return jcasType.ll_cas.ll_getIntValue(addr, ((PAtN_Type)jcasType).casFeatCode_n);}
    
  /** setter for n - sets The rank at which the precision at n was computed. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setN(int v) {
    if (PAtN_Type.featOkTst && ((PAtN_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "type.PAtN");
    jcasType.ll_cas.ll_setIntValue(addr, ((PAtN_Type)jcasType).casFeatCode_n, v);}    
  }

    