package sakila;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Sakila Restful Search Model
 *
 * @author aruld@acm.org
 */
@Entity
@Table(name = "sales_by_film_category")
@NamedQueries({
    @NamedQuery(name = "SalesByFilmCategory.findAll", query = "SELECT s FROM SalesByFilmCategory s"),
    @NamedQuery(name = "SalesByFilmCategory.findByCategory", query = "SELECT s FROM SalesByFilmCategory s WHERE s.category = :category"),
    @NamedQuery(name = "SalesByFilmCategory.findByTotalSales", query = "SELECT s FROM SalesByFilmCategory s WHERE s.totalSales = :totalSales")})
public class SalesByFilmCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @Column(name = "category")
    private String category;
    @Column(name = "total_sales")
    private BigDecimal totalSales;

    public SalesByFilmCategory() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

}
