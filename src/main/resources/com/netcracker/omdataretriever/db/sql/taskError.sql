select *
  from task_instnc_error
 where task_instnc_oid in
       (select oid
          from cygent_admin.task_instnc
         where process_flow_instnc_oid in
               (select oid
                  from cygent_admin.process_flow_instnc
                 where target_oid in
                       (select oid
                          from cygent_admin.ordr_item
                         where ordr_oid = ?)))
 order by error_tmstmp desc