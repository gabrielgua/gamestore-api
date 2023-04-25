alter table pedido add constraint fk_pedido_forma_pagamento
foreign key (forma_pagamento_id) references forma_pagamento (id);