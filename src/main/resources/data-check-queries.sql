select size,
       count(*) -- size'ı olmayan ürünler var
from product
group by size;


select age,
       count(*) --age'i new olan bir ürün var ve age'i hiç olmayan bisürü ürün var
from product
group by age;


select category_path,
       count(*) --kategorisi olmayan ürün var
from product
group by category_path;


select leaf_category,
       count(*) --kategorisi olmayan ürün var
from product
group by leaf_category;


select sku_number,
       length_by_cm,
       status,
       stock_status,
       quantity --ürün var hüseyin abiye girilmesi için haber verildi.
from product
where length_by_cm = 0
   or length_by_cm is null;


select sku_number,
       length_by_inches,
       status,
       stock_status,
       quantity
from product
where length_by_inches = ''
   or length_by_inches is null;


select sku_number,
       main_image_url,
       status,
       stock_status,
       quantity
from product
where main_image_url = ''
   or main_image_url is null;


select sku_number,
       meta_description,
       status,
       stock_status,
       quantity
from product
where meta_description = ''
   or meta_description is null;


select sku_number,
       name,
       status,
       stock_status,
       quantity
from product
where name = ''
   or name is null;


select sku_number,
       price,
       status,
       stock_status,
       quantity --bunda ürün var ama satılık değil
from product
where price = 0
   or price is null;


select sku_number,
       product_type,
       status,
       stock_status,
       quantity --bunda ürün var bakılmalı
from product
where product_type is null
   or product_type = '';


select quantity,
       count(*) --status, quantity ve stock status sayıları eşit olmalı ama değiller.
from product
group by quantity;


select region,
       count(*) -- region'ı olmayan var ve Oushak ile Turkish Oushak olanlar var.
from product
group by region;


select status,
       count(*) --status, quantity ve stock status sayıları eşit olmalı ama değiller.
from product
group by status;


select stock_status,
       count(*) --3 çeşit stock status var bunlar ikiye indirilebilir.
from product
group by stock_status;


select weave,
       count(*) -- weave olmayan ürünler var.
from product
group by weave;


select sku_number, ----ürün var hüseyin abiye girilmesi için haber verildi.
       width_by_cm,
       status,
       stock_status,
       quantity
from product
where width_by_cm is null
   or width_by_cm = 0;


select sku_number, ----ürün var hüseyin abiye girilmesi için haber verildi.
       width_by_inches,
       status,
       stock_status,
       quantity
from product
where width_by_inches is null
   or width_by_inches = '';

select colors,
       count(*) -- yazım yanlışı olanlar falan var onlar düzeltilebilir.
from product
         inner join product_colors pc on product.id = pc.product_id
group by colors;


select materials,
       count(*) -- yazım yanlışı olanlar falan var onlar düzeltilebilir.
from product
         inner join product_materials pm on product.id = pm.product_id
group by materials;


select styles,
       count(*) -- yazım yanlışı olanlar falan var onlar düzeltilebilir.
from product
         inner join product_styles ps on product.id = ps.product_id
group by styles;


select sku_number,
       meta_keyword,
       status,
       stock_status,
       quantity
from product
         inner join product_meta_keyword pmk on product.id = pmk.product_id
where meta_keyword is null
   or meta_keyword = '';


select sku_number,
       additional_image_paths,
       status,
       stock_status,
       quantity
from product
         inner join product_additional_image_paths paip on product.id = paip.product_id
where additional_image_paths is null
   or additional_image_paths = '';