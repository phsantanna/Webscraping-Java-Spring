create table tb_produtos(id bigint not null, nome_produto varchar not null, preco_final varchar not null, url_produto varchar not null);
create sequence seq_id
    start 1
    increment 1
    owned by tb_produtos.id;