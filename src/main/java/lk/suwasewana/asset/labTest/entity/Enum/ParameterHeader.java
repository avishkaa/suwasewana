package lk.suwasewana.asset.labTest.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParameterHeader {
    Yes("Yes"),
    No("No");

    private final String parameterHeader;

}
