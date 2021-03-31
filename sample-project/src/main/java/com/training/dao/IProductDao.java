package com.training.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.training.entity.BrandEntity;
import com.training.entity.ProductEntity;

@Repository
public interface IProductDao extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
		
	ProductEntity findByProductName(String productName);
	
	ProductEntity findByProductId(Long productId);
                                  
	ProductEntity findByProductNameAndProductIdNot(String productName, Long productId);

	/**
	 * Get search condition
	 * 
	 * @param searchConditionsMap
	 * @return Specification<ProductEntity>()
	 */
	public static Specification<ProductEntity> getSearchCondition(Map<String, Object> searchConditionsMap ) {
		return new Specification<ProductEntity>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<ProductEntity> productRoot, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<Predicate>();
				if ( searchConditionsMap != null ) {
					String searchText = (String) searchConditionsMap.get("keyword");
					String priceFrom = (String) searchConditionsMap.get("priceFrom");
					String priceTo = (String) searchConditionsMap.get("priceTo");
					Join<ProductEntity, BrandEntity> brandRoot = productRoot.join("brandEntity");
					
					// Search text 
					if (StringUtils.isNotEmpty(searchText)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(productRoot.get("productName"), "%" + searchText + "%"),
								criteriaBuilder.like(brandRoot.get("brandName"), "%" + searchText +  "%")
								));
					}
					
					// Price from s
					if (StringUtils.isNotEmpty(priceFrom)) {
						predicates.add(criteriaBuilder.greaterThanOrEqualTo(productRoot.get("price"), Double.parseDouble(priceFrom)));
					}
					
					// Price to 
					if (StringUtils.isNotEmpty(priceTo)) {
						predicates.add(criteriaBuilder.lessThanOrEqualTo(productRoot.get("price"), Double.parseDouble(priceTo)));
					}
							
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
