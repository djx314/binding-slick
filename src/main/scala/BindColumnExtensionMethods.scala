package org.scalax.binding.slick

import slick.ast.{BaseTypedType, TypedType}
import slick.lifted.{ColumnOrdered, ColumnsShapeLevel, CompiledFunction, LiteralColumn, Query, Rep, Shape, TableQuery}
import slick.relational.RelationalProfile

trait BindColumnExtensionMethods {

  @deprecated("Use an explicit conversion to an Option column with `.?`", "3.0")
  implicit def columnToOptionColumn[T: BaseTypedType](c: Rep[T]): Rep[Option[T]]
  implicit def valueToConstColumn[T: TypedType](v: T): LiteralColumn[T]
  implicit def columnToOrdered[T: TypedType](c: Rep[T]): ColumnOrdered[T]
  implicit def tableQueryToTableQueryExtensionMethods[T <: RelationalProfile#Table[_], U](
    q: Query[T, U, Seq] with TableQuery[T]
  ): BindColumnExtensionMethods.TableQueryExtensionMethods[T, U]

  /*implicit def streamableCompiledInsertActionExtensionMethods[EU](c: StreamableCompiled[_, _, EU]): InsertActionExtensionMethods[EU] = createInsertActionExtensionMethods[EU](c.compiledInsert.asInstanceOf[CompiledInsert])
  implicit def queryInsertActionExtensionMethods[U, C[_]](q: Query[_, U, C]): InsertActionExtensionMethods[U] = createInsertActionExtensionMethods[U](compileInsert(q.toNode))

  implicit def schemaActionExtensionMethods(sd: SchemaDescription): SchemaActionExtensionMethods = createSchemaActionExtensionMethods(sd)

  implicit def fastPathExtensionMethods[T, P](mp: MappedProjection[T, P]): FastPathExtensionMethods[ResultConverterDomain, T, P] = new FastPathExtensionMethods[ResultConverterDomain, T, P](mp)*/

}

object BindColumnExtensionMethods {
  trait TableQueryExtensionMethods[T, U] {
    // def schema: SchemaDescription = buildTableSchemaDescription(q.shaped.value.asInstanceOf[Table[_]])
    def findBy[P](f: (T => Rep[P]))(
      implicit ashape: Shape[ColumnsShapeLevel, Rep[P], P, Rep[P]],
      pshape: Shape[ColumnsShapeLevel, P, P, _]
    ): CompiledFunction[Rep[P] => Query[T, U, Seq], Rep[P], P, Query[T, U, Seq], Seq[U]]
  }
}
