package ca.gbc.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="t_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * The @GeneratedValue annotation specifies how the primary key (ID) will be generated automatically by the database.
     *
     * strategy = GenerationType.IDENTITY:
     * - The database will handle auto-incrementing the primary key.
     * - Hibernate relies on the underlying database's auto-increment feature to generate unique IDs.
     * - Each time a new record is inserted, the database assigns the next available primary key value.
     *
     * Other strategies:
     *
     * 1. GenerationType.AUTO:
     * - Hibernate chooses the strategy based on the underlying database's capabilities (could be SEQUENCE, TABLE, or IDENTITY).
     * - This is the default strategy if none is specified.
     *
     * 2. GenerationType.SEQUENCE:
     * - Uses a database sequence object to generate values for the primary key.
     * - Commonly used in databases that support sequences (e.g., PostgreSQL, Oracle).
     * - Allows preallocation of ID values in batches for performance improvements.
     *
     * 3. GenerationType.TABLE:
     * - Uses a separate table in the database to generate primary key values.
     * - Can be used across multiple tables, but it is generally slower compared to other strategies.
     * - It provides a more flexible approach but is less commonly used.
     */
    private Long id;

    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
