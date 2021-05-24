delete from account;
delete from "transaction";

alter sequence account_sequence restart with 1;
alter sequence transaction_sequence restart with 1;
