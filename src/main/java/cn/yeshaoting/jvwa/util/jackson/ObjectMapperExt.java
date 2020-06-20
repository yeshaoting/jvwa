package cn.yeshaoting.jvwa.util.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

/**
 * @description
 * 
 * @author yeshaoting
 * @date 2015-08-19 18:45:41
 */
@SuppressWarnings("serial")
public class ObjectMapperExt extends ObjectMapper {
  
  public ObjectMapperExt() {
    super();
    this.disable(MapperFeature.USE_ANNOTATIONS);
  }
  
  public ObjectMapperExt(JsonFactory jf, DefaultSerializerProvider sp,
      DefaultDeserializationContext dc) {
    super(jf, sp, dc);
    this.disable(MapperFeature.USE_ANNOTATIONS);
  }
  
  public ObjectMapperExt(JsonFactory jf) {
    super(jf);
    this.disable(MapperFeature.USE_ANNOTATIONS);
  }
  
  public ObjectMapperExt(ObjectMapper src) {
    super(src);
    this.disable(MapperFeature.USE_ANNOTATIONS);
  }
  
  
}
