DROP TABLE IF EXISTS `coffees`;
CREATE TABLE `coffees`
(
    `id`         bigint unsigned NOT NULL AUTO_INCREMENT,
    `name`       varchar(255)    NOT NULL DEFAULT '' COMMENT '名称',
    `price`      bigint                   DEFAULT NULL COMMENT '价格',
    `created_at` datetime                 DEFAULT NULL,
    `updated_at` datetime                 DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;