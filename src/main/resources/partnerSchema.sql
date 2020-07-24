create or replace PACKAGE BODY PKG_PARTNERS
AS
  PROCEDURE PRC_GET_PARTNERS(
      o_ret OUT SYS_REFCURSOR,
      i_name       IN VARCHAR2,
      i_parent_id  IN NUMBER,
      i_delegate   IN VARCHAR2,
      i_note       IN VARCHAR2,
      i_status     IN NUMBER,
      i_skip       IN NUMBER,
      i_take       IN NUMBER,
      i_order_by   IN VARCHAR2,
      i_order_type IN VARCHAR2 )
  AS
  BEGIN
    OPEN o_ret FOR
    SELECT PARTNERS.id,PARTNERS.name,p2.name AS partner_parent,PARTNERS.delegate,PARTNERS.note,PARTNERS.status
    FROM PARTNERS
    LEFT JOIN PARTNERS p2
    ON PARTNERS.PARENT_ID = p2.id
    WHERE (i_name IS NULL OR LOWER(PARTNERS.name) LIKE LOWER('%' || i_name || '%'))
      AND (i_parent_id IS NULL OR PARTNERS.PARENT_ID = i_parent_id)
      AND (i_delegate IS NULL OR LOWER(PARTNERS.delegate) LIKE LOWER('%' || i_delegate || '%'))
      AND (i_note IS NULL OR LOWER(PARTNERS.delegate) LIKE LOWER('%' || i_note || '%'))
      AND (i_status IS NULL OR PARTNERS.STATUS = i_status)
    ORDER BY
      CASE WHEN LOWER(i_order_by) = 'partner_parent' AND LOWER(i_order_type) = 'asc' THEN NLSSORT(partner_parent ,'NLS_SORT = VIETNAMESE') END,
      CASE WHEN LOWER(i_order_by) = 'partner_parent' AND LOWER(i_order_type) = 'desc' THEN NLSSORT(partner_parent ,'NLS_SORT = VIETNAMESE')END DESC,
      CASE WHEN LOWER(i_order_by) = 'created_at' AND LOWER(i_order_type) = 'asc' THEN PARTNERS.CREATED_AT END,
      CASE WHEN LOWER(i_order_by) = 'created_at' AND LOWER(i_order_type) = 'desc' THEN PARTNERS.CREATED_AT END DESC
    OFFSET i_skip ROWS FETCH NEXT i_take ROWS ONLY;
  END PRC_GET_PARTNERS;
  PROCEDURE PRC_COUNT_PARTNERS(
      o_ret OUT SYS_REFCURSOR,
      i_name      IN VARCHAR2,
      i_parent_id IN NUMBER,
      i_delegate  IN VARCHAR2,
      i_note      IN VARCHAR2,
      i_status    IN NUMBER)
  AS
  BEGIN
    OPEN o_ret FOR
    SELECT COUNT(id) AS results
    FROM PARTNERS
    WHERE (i_name IS NULL OR LOWER(PARTNERS.name) LIKE LOWER('%' || i_name || '%'))
      AND (i_parent_id IS NULL OR PARTNERS.PARENT_ID = i_parent_id)
      AND (i_delegate IS NULL OR LOWER(PARTNERS.delegate) LIKE LOWER('%' || i_delegate || '%'))
      AND (i_note IS NULL OR LOWER(PARTNERS.delegate) LIKE LOWER('%' || i_note || '%'))
      AND (i_status IS NULL OR PARTNERS.STATUS = i_status);
  END PRC_COUNT_PARTNERS;
  PROCEDURE PRC_EXPORT_PARTNERS(
      o_ret OUT SYS_REFCURSOR,
      i_name       IN VARCHAR2,
      i_parent_id  IN NUMBER,
      i_delegate   IN VARCHAR2,
      i_note       IN VARCHAR2,
      i_status     IN NUMBER,
      i_order_by   IN VARCHAR2,
      i_order_type IN VARCHAR2 )
  AS
  BEGIN
    OPEN o_ret FOR
    SELECT PARTNERS.id,PARTNERS.name,p2.name  AS partner_parent,PARTNERS.delegate,PARTNERS.note,PARTNERS.status
    FROM PARTNERS
    LEFT JOIN PARTNERS p2
    ON PARTNERS.PARENT_ID = p2.id
    WHERE (i_name IS NULL OR LOWER(PARTNERS.name) LIKE LOWER('%' || i_name || '%'))
    AND (i_parent_id IS NULL OR PARTNERS.PARENT_ID = i_parent_id)
    AND (i_delegate IS NULL OR LOWER(PARTNERS.delegate) LIKE LOWER('%' || i_delegate || '%'))
    AND (i_note IS NULL OR LOWER(PARTNERS.delegate) LIKE LOWER('%' || i_note || '%'))
    AND (i_status IS NULL OR PARTNERS.STATUS = i_status)
    ORDER BY
      CASE WHEN LOWER(i_order_by) = 'partner_parent' AND LOWER(i_order_type) = 'asc' THEN NLSSORT(partner_parent ,'NLS_SORT = VIETNAMESE') END,
      CASE WHEN LOWER(i_order_by) = 'partner_parent' AND LOWER(i_order_type) = 'desc' THEN NLSSORT(partner_parent ,'NLS_SORT = VIETNAMESE') END DESC,
      CASE WHEN LOWER(i_order_by) = 'created_at' AND LOWER(i_order_type) = 'asc' THEN PARTNERS.CREATED_AT END,
      CASE WHEN LOWER(i_order_by) = 'created_at' AND LOWER(i_order_type) = 'desc' THEN PARTNERS.CREATED_AT END DESC;
  END PRC_EXPORT_PARTNERS;
  PROCEDURE PRC_GET_PARTNERS_PARENT(
      o_ret OUT SYS_REFCURSOR )
  AS
  BEGIN
    OPEN o_ret FOR
    SELECT p2.ID,p1.NAME
    FROM PARTNERS p1
    LEFT JOIN (SELECT DISTINCT(parent_id) AS ID FROM PARTNERS WHERE parent_id IS NOT NULL) p2
    ON p1.ID = p2.ID
    WHERE p2.ID IS NOT NULL
    ORDER BY P1.CREATED_AT DESC;
  END PRC_GET_PARTNERS_PARENT;
  PROCEDURE PRC_GET_PARTNER_BY_ID(
      o_ret OUT SYS_REFCURSOR,
      i_partner_id IN NUMBER )
  AS
  BEGIN
    OPEN o_ret FOR
    SELECT PARTNERS.id,PARTNERS.name,p2.name AS partner_parent,PARTNERS.delegate,PARTNERS.note,PARTNERS.status
    FROM PARTNERS
    LEFT JOIN PARTNERS p2
    ON PARTNERS.PARENT_ID = p2.id
    WHERE PARTNERS.id = i_partner_id ;
  END PRC_GET_PARTNER_BY_ID;
  PROCEDURE PRC_GET_PARTNER_PARENT_BY_ID(
      o_ret OUT SYS_REFCURSOR,
      i_partner_id IN NUMBER )
  AS
  BEGIN
    OPEN o_ret FOR
     SELECT p2.ID,p1.NAME
    FROM PARTNERS p1
    LEFT JOIN (SELECT DISTINCT(parent_id) AS ID FROM PARTNERS WHERE parent_id IS NOT NULL) p2
    ON p1.ID = p2.ID
    WHERE p2.ID  = i_partner_id;
  END PRC_GET_PARTNER_PARENT_BY_ID;
  PROCEDURE PRC_DELETE_PARTNERS(
      o_ret OUT SYS_REFCURSOR,
      i_partner_ids IN VARCHAR2 )
  AS
  BEGIN
    DELETE PARTNERS
    WHERE ID IN(SELECT regexp_substr(txt,'[^,]+', 1, level) token
      FROM
        (SELECT i_partner_ids txt FROM dual)
        CONNECT BY regexp_substr(txt, '[^,]+', 1, level) IS NOT NULL
      );
    OPEN o_ret FOR SELECT i_partner_ids
  AS
    PARTNERS_IDS FROM dual;
  END PRC_DELETE_PARTNERS;
  PROCEDURE PRC_CREATE_PARTNER(
      o_ret OUT SYS_REFCURSOR,
      i_code NVARCHAR2,
      i_name NVARCHAR2,
      i_parent_id     NUMBER,
      i_supplier_type NUMBER,
      i_customer_type NUMBER,
      i_delegate NVARCHAR2,
      i_note NVARCHAR2,
      i_status NUMBER,
      i_ip_partner NVARCHAR2 )
  AS
  BEGIN
    INSERT
    INTO INFOTRADING_ESB_WEB.PARTNERS
      (CODE,NAME,PARENT_ID,SUPPLIER_TYPE,CUSTOMER_TYPE,DELEGATE,NOTE,STATUS,IP_PARTNER)
    VALUES(i_code,i_name,i_parent_id,i_supplier_type,i_customer_type,i_delegate,i_note,i_status,i_ip_partner);
  END PRC_CREATE_PARTNER;
  PROCEDURE PRC_GET_PARTNER_BY_CODE
    (
      o_ret OUT SYS_REFCURSOR,
      i_code IN NVARCHAR2
    )
  AS
  BEGIN
    OPEN o_ret FOR SELECT PARTNERS.ID,
    PARTNERS.NAME,
    PARTNERS.CODE,
    PARTNERS.PARENT_ID,
    PARTNERS.SUPPLIER_TYPE,
    PARTNERS.CUSTOMER_TYPE ,
    PARTNERS.DELEGATE,
    PARTNERS.STATUS,
    PARTNERS.IP_PARTNER FROM PARTNERS WHERE PARTNERS.CODE = i_code ;
  END PRC_GET_PARTNER_BY_CODE;
END PKG_PARTNERS;