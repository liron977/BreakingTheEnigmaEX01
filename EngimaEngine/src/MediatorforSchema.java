import schemaGenerated.CTEEnigma;

public class MediatorforSchema {

  private SchemaGenerated schemaGenerated;

  public MediatorforSchema(CTEEnigma enigmaDescriptor){
     this.schemaGenerated=new SchemaGenerated(enigmaDescriptor);
  }
  public boolean xmlValidation(){

      int result= schemaGenerated.isXmlValid();
      if(result==100){
          return true;
      }
      else {
          return false;
      }

  }
 }
