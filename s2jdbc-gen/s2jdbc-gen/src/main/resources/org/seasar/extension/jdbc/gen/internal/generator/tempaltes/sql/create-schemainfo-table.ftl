${keyword("create table")} ${name} (
    ${columnName} ${columnDefinition}
)<#if tableOption??> ${tableOption}</#if>${delimiter}
${keyword("insert into")} ${name} (${columnName}) ${keyword("values")} (${versionNo})${delimiter}
