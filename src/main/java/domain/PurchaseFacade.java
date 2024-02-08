package domain;

import data.Purchase;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class PurchaseFacade {

    private final Purchase purchase;

    public boolean isPurchaseCancelled() {
        return "cancelled".equalsIgnoreCase(purchase.getStatus());
    }

    public boolean hasEmptyTags() {
        return purchase.getTags().isEmpty();
    }

    public String getFirstTag() {
        return purchase.getTags().stream().findFirst().orElse("");
    }

    public String getDateAsString() {
        return purchase.getDate().toString();
    }

}
