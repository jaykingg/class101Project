package net.class101.server1.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductPayDto {
    List<String> payDetails;
    Integer orderAmount;
    Integer payAmount;
}
