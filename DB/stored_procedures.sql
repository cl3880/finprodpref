/*
 * =============================================================================
 * E.SUN Bank Financial Product Preference System - Stored Procedures
 * =============================================================================
 * Author: Chris Leu
 * Created: 2025-07-25
 * 
 * Description: 
 *   Stored procedures for managing user favorite financial products.
 *   All database operations must use these procedures per E.SUN requirements.
 * 
 * Notes:
 *   - All inputs are parameterized to prevent SQL injection
 * =============================================================================
 */

/*
 * Procedure: sp_add_favorite
 * Purpose: 新增喜好金融商品
 * Params:
 *   p_user_id: 使用者 ID
 *   p_product_no: 產品流水號 
 *   p_quantity: 購買數量
 *   p_account: 扣款帳號
 * Returns: Success flag and message
 * Note: Rejects if (user_id, product_no, account) combination already exists.
 */
CREATE OR REPLACE FUNCTION sp_add_favorite(
    p_user_id VARCHAR(10),
    p_product_no VARCHAR(50),
    p_quantity INTEGER,
    p_account VARCHAR(20)
) 
RETURNS TABLE(success BOOLEAN, message TEXT) 
AS $$
BEGIN
    IF p_quantity <= 0 THEN
        RETURN QUERY SELECT FALSE, 'Quantity must be greater than 0';
        RETURN;
    END IF;

    INSERT INTO like_list (user_id, product_no, order_name, account, total_fee, total_amount)
    SELECT 
        p_user_id,
        p.no,
        p_quantity,
        p_account,
        ROUND(p.price * p_quantity * p.fee_rate, 2),
        ROUND((p.price * p_quantity) + (p.price * p_quantity * p.fee_rate), 2)
    FROM products p
    WHERE p.no = p_product_no;

    IF FOUND THEN
        RETURN QUERY SELECT TRUE, 'Success';
    ELSE
        RETURN QUERY SELECT FALSE, 'Product not found';
    END IF;

EXCEPTION
    WHEN unique_violation THEN
        RETURN QUERY SELECT FALSE, 'This product is already a favorite for the selected account.';
END;
$$ LANGUAGE plpgsql;

/*
 * Procedure: sp_get_favorites
 * Purpose: 查詢喜好金融商品清單
 * Parameters:
 *   p_user_id: 使用者 ID
 * Returns: 產品名稱清單、扣款帳號、預計扣款總金額、總手續費用、使用者電子信箱
 */
CREATE OR REPLACE FUNCTION sp_get_favorites(p_user_id VARCHAR(10))
RETURNS TABLE(
    product_name VARCHAR(200),
    account VARCHAR(20),
    total_amount DECIMAL(10,2),
    total_fee DECIMAL(10,2),
    user_email VARCHAR(100)
) 
AS $$
BEGIN
    RETURN QUERY
    SELECT 
        p.product_name,
        l.account,
        l.total_amount,
        l.total_fee,
        u.email
    FROM like_list l
    JOIN products p ON l.product_no = p.no
    JOIN users u ON l.user_id = u.user_id
    WHERE l.user_id = p_user_id
    ORDER BY p.product_name, l.account;
END;
$$ LANGUAGE plpgsql;

/*
 * Procedure: sp_delete_favorite
 * Purpose: 刪除喜好金融商品資訊
 * Parameters:
 *   p_user_id: 使用者 ID
 *   p_product_no: 產品流水號
 *   p_account: 扣款帳號
 * Returns: Success flag and message
 */
CREATE OR REPLACE FUNCTION sp_delete_favorite(
    p_user_id VARCHAR(10),
    p_product_no VARCHAR(50),
    p_account VARCHAR(20)
) 
RETURNS TABLE(success BOOLEAN, message TEXT) 
AS $$
BEGIN
    DELETE FROM like_list 
    WHERE user_id = p_user_id AND product_no = p_product_no AND account = p_account;
    
    IF FOUND THEN
        RETURN QUERY SELECT TRUE, 'Success';
    ELSE
        RETURN QUERY SELECT FALSE, 'Not found';
    END IF;
END;
$$ LANGUAGE plpgsql;

/*
 * Procedure: sp_update_favorite
 * Purpose: 更改喜好金融商品資訊
 * Parameters:
 *   p_user_id: 使用者 ID
 *   p_product_no: 產品流水號
 *   p_old_account: 舊的扣款帳號 (to identify the record)
 *   p_new_account: 新的扣款帳號
 *   p_quantity: 購買數量
 * Returns: Success flag and message
 */
CREATE OR REPLACE FUNCTION sp_update_favorite(
    p_user_id VARCHAR(10),
    p_product_no VARCHAR(50),
    p_old_account VARCHAR(20),
    p_new_account VARCHAR(20),
    p_quantity INTEGER
) 
RETURNS TABLE(success BOOLEAN, message TEXT) 
AS $$
BEGIN
    IF p_quantity <= 0 THEN
        RETURN QUERY SELECT FALSE, 'Quantity must be greater than 0';
        RETURN;
    END IF;

    UPDATE like_list l
    SET 
        order_name = p_quantity,
        account = p_new_account,
        total_fee = ROUND(p.price * p_quantity * p.fee_rate, 2),
        total_amount = ROUND((p.price * p_quantity) + (p.price * p_quantity * p.fee_rate), 2)
    FROM products p
    WHERE l.user_id = p_user_id 
    AND l.product_no = p_product_no
    AND l.account = p_old_account
    AND p.no = l.product_no;

    IF FOUND THEN
        RETURN QUERY SELECT TRUE, 'Success';
    ELSE
        RETURN QUERY SELECT FALSE, 'Not found';
    END IF;

EXCEPTION
    WHEN unique_violation THEN
        RETURN QUERY SELECT FALSE, 'Favorite with this product and account already exists.';

END;
$$ LANGUAGE plpgsql;

/*
 * Procedure: sp_get_user_accounts
 * Purpose: 取得使用者所有帳號清單
 * Parameters:
 *   p_user_id: 使用者 ID
 * Returns: 帳號清單
 */
CREATE OR REPLACE FUNCTION sp_get_user_accounts(p_user_id VARCHAR(10))
RETURNS TABLE(account_no VARCHAR(20))
AS $$
BEGIN
    RETURN QUERY
    SELECT ua.account_no
    FROM user_accounts ua
    WHERE ua.user_id = p_user_id
    ORDER BY ua.account_no;
END;
$$ LANGUAGE plpgsql;

/*
* View: v_product_list
* Purpose: Simple view for querying all products
*/
CREATE OR REPLACE VIEW v_product_list AS
SELECT 
    no AS product_no,
    product_name,
    price,
    fee_rate,
    ROUND(fee_rate * 100, 2) AS fee_rate_percent
FROM products
ORDER BY product_name;