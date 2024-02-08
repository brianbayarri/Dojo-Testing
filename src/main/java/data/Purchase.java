package data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Purchase {
    private String id;
    private String status;
    private String statusDetail;
    private List<String> tags;
    private LocalDate date;
    private String currencyId;
    private String safetyKeyword;
    private Context context;
}
