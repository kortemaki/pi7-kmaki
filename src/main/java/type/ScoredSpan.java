

/* First created by JCasGen Mon Oct 05 10:08:06 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** A span with a score
 * Updated by JCasGen Mon Oct 19 15:25:59 EDT 2015
 * XML source: /media/maki/OS/Users/Keith/Documents/CMU/Coursework/11791/PI7/pi7-kmaki/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class ScoredSpan extends SpanModification {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ScoredSpan.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ScoredSpan() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ScoredSpan(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ScoredSpan(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ScoredSpan(JCas jcas, int begin, int end) {
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
  //* Feature: score

  /** getter for score - gets The score for this annotation
   * @generated
   * @return value of the feature 
   */
  public Score getScore() {
    if (ScoredSpan_Type.featOkTst && ((ScoredSpan_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "type.ScoredSpan");
    return (Score)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ScoredSpan_Type)jcasType).casFeatCode_score)));}
    
  /** setter for score - sets The score for this annotation 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(Score v) {
    if (ScoredSpan_Type.featOkTst && ((ScoredSpan_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "type.ScoredSpan");
    jcasType.ll_cas.ll_setRefValue(addr, ((ScoredSpan_Type)jcasType).casFeatCode_score, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    