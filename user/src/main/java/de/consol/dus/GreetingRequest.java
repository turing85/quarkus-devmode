package de.consol.dus;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class GreetingRequest {
  String salutation;
  String name;
}
