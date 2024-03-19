package util;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import com.fasterxml.jackson.databind.ObjectMapper; // Import ObjectMapper if using Jackson for JSON serialization
import entity.Hero;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class HeroMessageBodyWriter implements MessageBodyWriter<Hero> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == Hero.class;
    }

    @Override
    public void writeTo(Hero hero, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException {
        // Serialize the hero object to JSON and write it to the output stream
        // Example: Use Jackson ObjectMapper to serialize to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(entityStream, hero);
    }

    @Override
    public long getSize(Hero hero, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType) {
        // Deprecated method, returning -1 indicates the size is unknown
        return -1;
    }
}
