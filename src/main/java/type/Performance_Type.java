
/* First created by JCasGen Mon Oct 05 10:45:47 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Annotates performance of the system on a single test element.
 * Updated by JCasGen Mon Oct 12 20:41:17 EDT 2015
 * @generated */
public class Performance_Type extends ComponentAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Performance_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Performance_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Performance(addr, Performance_Type.this);
  			   Performance_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Performance(addr, Performance_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Performance.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.Performance");
 
  /** @generated */
  final Feature casFeat_testElement;
  /** @generated */
  final int     casFeatCode_testElement;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTestElement(int addr) {
        if (featOkTst && casFeat_testElement == null)
      jcas.throwFeatMissing("testElement", "type.Performance");
    return ll_cas.ll_getRefValue(addr, casFeatCode_testElement);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTestElement(int addr, int v) {
        if (featOkTst && casFeat_testElement == null)
      jcas.throwFeatMissing("testElement", "type.Performance");
    ll_cas.ll_setRefValue(addr, casFeatCode_testElement, v);}
    
  
 
  /** @generated */
  final Feature casFeat_metrics;
  /** @generated */
  final int     casFeatCode_metrics;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getMetrics(int addr) {
        if (featOkTst && casFeat_metrics == null)
      jcas.throwFeatMissing("metrics", "type.Performance");
    return ll_cas.ll_getRefValue(addr, casFeatCode_metrics);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMetrics(int addr, int v) {
        if (featOkTst && casFeat_metrics == null)
      jcas.throwFeatMissing("metrics", "type.Performance");
    ll_cas.ll_setRefValue(addr, casFeatCode_metrics, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Performance_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_testElement = jcas.getRequiredFeatureDE(casType, "testElement", "type.TestElementAnnotation", featOkTst);
    casFeatCode_testElement  = (null == casFeat_testElement) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_testElement).getCode();

 
    casFeat_metrics = jcas.getRequiredFeatureDE(casType, "metrics", "uima.cas.FSList", featOkTst);
    casFeatCode_metrics  = (null == casFeat_metrics) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_metrics).getCode();

  }
}



    