package xyz.gauravsharma.rediscachedemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import xyz.gauravsharma.rediscachedemo.dao.ProductDao;
import xyz.gauravsharma.rediscachedemo.entity.Product;

import java.util.List;

@Service
@EnableCaching
public class ProductService {

    @Autowired
    private ProductDao dao;

    public Product save(Product product) {
        return dao.save(product);
    }

    public List<Product> findAll() {
        return dao.findAll();
    }

    @Cacheable(key = "#id", value = ProductDao.HASH_KEY, unless = "#result.qty>15")
    public Product findProductById(int id) {
        return dao.findProductById(id);
    }

    @CacheEvict(key = "#id", value = ProductDao.HASH_KEY)
    public String deleteProduct(int id) {
        return dao.deleteProduct(id);
    }
}
