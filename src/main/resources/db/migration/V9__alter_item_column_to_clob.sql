-- Adicionar nova coluna CLOB
ALTER TABLE operation_logs ADD item_new CLOB;

-- Copiar dados (se houver) - opcional, pois RAW serializado pode não ser compatível
-- UPDATE operation_logs SET item_new = item WHERE item IS NOT NULL;

-- Remover coluna antiga
ALTER TABLE operation_logs DROP COLUMN item;

-- Renomear nova coluna
ALTER TABLE operation_logs RENAME COLUMN item_new TO item;