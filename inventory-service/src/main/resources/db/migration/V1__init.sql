CREATE TABLE `t_inventory`
(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `sku_code` varchar(255) DEFAUlT NULL,
    `quantity` int(11) DEFAUlT NULL,
    PRIMARY KEY (`id`)
);