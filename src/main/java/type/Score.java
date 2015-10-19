

/* First created by JCasGen Mon Oct 19 14:30:37 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** A double value that remembers the name of the component which annotated it.
 * Updated by JCasGen Mon Oct 19 15:25:59 EDT 2015
 * XML source: /media/maki/OS/Users/Keith/Documents/CMU/Coursework/11791/PI7/pi7-kmaki/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class Score extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Score.class);
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
  protected Score() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Score(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Score(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Score(JCas jcas, int begin, int end) {
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

  /** getter for score - gets The value annotated by this annotation.
   * @generated
   * @return value of the feature 
   */
  public double getScore() {
    if (Score_Type.featOkTst && ((Score_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "type.Score");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Score_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets The value annotated by this annotation. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(double v) {
    if (Score_Type.featOkTst && ((Score_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "type.Score");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Score_Type)jcasType).casFeatCode_score, v);}    
  }

    