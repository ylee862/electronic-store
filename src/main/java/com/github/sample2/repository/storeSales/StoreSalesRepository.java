package com.github.sample2.repository.storeSales;

public interface StoreSalesRepository {
    StoreSales findStoreSalesById(Integer storeId);

    void updateSalesAmount(Integer storeId, Integer stock);
}
