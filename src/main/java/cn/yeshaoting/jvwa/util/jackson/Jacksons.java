package cn.yeshaoting.jvwa.util.jackson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Jacksons {
  
  private ObjectMapper objectMapper;
  
  public static Jacksons build() {
    return new Jacksons();
  }
  
  private Jacksons() {
    objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    objectMapper.enable(MapperFeature.USE_ANNOTATIONS);
    
    // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
  }
  
  public Jacksons filter(String filterName, String... properties) {
    FilterProvider filterProvider =
        new SimpleFilterProvider().addFilter(filterName,
            SimpleBeanPropertyFilter.serializeAllExcept(properties));
    objectMapper.setFilters(filterProvider);
    return this;
  }
  
  /*
   * public Jacksons addMixInAnnotations(Class<?> target, Class<?> mixinSource) {
   * objectMapper.getSerializationConfig().addMixInAnnotations(target, mixinSource);
   * objectMapper.getDeserializationConfig().addMixInAnnotations(target, mixinSource); return this;
   * }
   */
  
  public Jacksons setDateFormate(DateFormat dateFormat) {
    objectMapper.setDateFormat(dateFormat);
    return this;
  }
  
  public <T> T json2Obj(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("解析json错误");
    }
  }
  
  public String readAsString(Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("解析对象错误");
    }
  }
  
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> json2List(String json) {
    try {
      return objectMapper.readValue(json, List.class);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("解析json错误");
    }
  }
  
  public <T> T convertValue(Object fromValue, Class<T> toValueType) throws IllegalArgumentException {
    return objectMapper.convertValue(fromValue, toValueType);
  }
  
  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }
  
}
