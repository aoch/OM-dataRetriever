SELECT ID,
       TASK_DEFN_ID,
       NAME,
       DSCR,
       CASE TYPE
         WHEN 0 THEN
          'SYNC'
         WHEN 1 THEN
          'AUTOMATIC'
         WHEN 2 THEN
          'MANUAL'
         WHEN 3 THEN
          'END'
         WHEN 4 THEN
          'SCRIPT'
         WHEN 5 THEN
          'START'
       END TASK_TYPE,
       CASE STATE_CD
         WHEN 0 THEN
          'NEW'
         WHEN 1 THEN
          'SCHEDULED'
         WHEN 2 THEN
          'ACTIVE'
         WHEN 3 THEN
          'PENDING_COMPLETE'
         WHEN 4 THEN
          'COMPLETE'
         WHEN 5 THEN
          'PENDING_TERMINATE'
         WHEN 6 THEN
          'TERMINATED'
         WHEN 7 THEN
          'FAILED'
         WHEN 8 THEN
          'NOT_INSTANTIATED'
         WHEN 9 THEN
          'ERROR'
       END TASK_STATUS,
       (CAST(NVL(END_DT, SYSDATE) AS TIMESTAMP) -
       CAST(CREATE_DT AS TIMESTAMP)) ELAPSE_DTM,
       PROCESS_FLOW_INSTNC_OID,
       ROLE_OID,
       PREV_TASK_INSTNC_OID,
       ASSGND_PERFRMR_OID,
       LAST_MOD_PERFRMR_OID,
       PERFRMR_LAST_MOD_DT,
       ESCLTN_FLG,
       ATTACHMENT,
       SERVER_NAME,
       START_DT,
       END_DT,
       CREATE_DT,
       WRITE_LOCK,
       INTRCTN_MODEL_INSTNC_STATE_OID,
       ERROR_CD,
       ERROR_PERFRMR_OID
  FROM (SELECT T.*, ROWNUM
          FROM CYGENT_ADMIN.TASK_INSTNC T
         WHERE T.PROCESS_FLOW_INSTNC_OID IN
               (SELECT TO_NUMBER(ID)
                  FROM CYGENT_ADMIN.PROCESS_FLOW_INSTNC PFI,
                       (SELECT TO_NUMBER( ? ) TARGET_OID,
                               'Order' TARGET_CLASS
                          FROM DUAL
                        UNION ALL
                        SELECT TO_NUMBER(OID) TARGET_OID,
                               'OrderItem' TARGET_CLASS
                          FROM CYGENT_ADMIN.ORDR_ITEM OI
                         WHERE OI.ORDR_OID = TO_NUMBER( ? )) ORDR
                 WHERE PFI.TARGET_OID = TO_CHAR(ORDR.TARGET_OID)
                   AND PFI.TARGET_CLASS = ORDR.TARGET_CLASS)) T
 START WITH T.PREV_TASK_INSTNC_OID IS NULL
CONNECT BY PRIOR T.OID = T.PREV_TASK_INSTNC_OID