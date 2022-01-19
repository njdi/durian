package io.njdi.durian.xbatis.model.where;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.njdi.durian.xbatis.model.Expression;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@SuperBuilder
@JsonDeserialize(using = Where.WhereDeserializer.class)
@Slf4j
public abstract class Where extends Expression implements Serializable {
  @NoArgsConstructor
  public static class WhereDeserializer extends JsonDeserializer<Where> {
    private static final String NAME = "name";
    private static final String OPERATOR = "operator";
    private static final String VALUES = "values";

    private static final String FILTERS = "filters";

    private Filter deserializeFilter(JsonNode node) {
      log.info(node.toString());

      String name = node.get(NAME).asText();
      Filter.Operator operator = Filter.Operator.valueOf(node.get(OPERATOR).asText());

      List<Object> values = new ArrayList<>();
      for (JsonNode valueNode : node.get(VALUES)) {
        if (valueNode.isTextual()) {
          values.add(valueNode.asText());
        } else if (valueNode.isInt()) {
          values.add(valueNode.asInt());
        } else if (valueNode.isLong()) {
          values.add(valueNode.asLong());
        } else if (valueNode.isFloat() || valueNode.isDouble()) {
          values.add(valueNode.asDouble());
        }
      }

      return Filter.builder()
              .name(name)
              .operator(operator)
              .values(values)
              .build();
    }

    private OrFilter deserializeOrFilter(JsonNode tree) {
      log.info(tree.toString());

      List<Filter> filters = new ArrayList<>();

      for (JsonNode filterNode : tree.get(FILTERS)) {
        filters.add(deserializeFilter(filterNode));
      }

      return OrFilter.builder()
              .filters(filters)
              .build();
    }

    @Override
    public Where deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
      JsonNode tree = jsonParser.readValueAsTree();

      return tree.has(FILTERS) ? deserializeOrFilter(tree) : deserializeFilter(tree);
    }
  }
}