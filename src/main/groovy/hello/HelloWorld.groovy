package hello

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion
import com.networknt.schema.JsonSchema
//import com.networknt.schema.ValidationMessage

public class HelloWorld {
    public static void main(String[] args) {
		File YML_FILE = new File("configs/jpl_1.yml")
    	File SCHEMA_FILE = new File("schema/jpl_schema.json")

		ObjectMapper objMapper = new ObjectMapper(new YAMLFactory())

		JsonSchemaFactory factory = JsonSchemaFactory.builder(
            JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7))
        .objectMapper(objMapper).build()
		
		Set invalidMessages = factory.getSchema(SCHEMA_FILE.text)
            .validate(objMapper.readTree(YML_FILE.text))
            .message
		if(!invalidMessages.empty){	
			invalidMessages.each{ println it }
		}
    }
}
