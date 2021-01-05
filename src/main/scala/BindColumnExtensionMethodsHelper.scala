package org.scalax.binding.slick

import slick.ast.{BaseTypedType, TypedType}
import slick.jdbc.JdbcProfile
import slick.lifted.{BaseColumnExtensionMethods, ColumnOrdered, ColumnsShapeLevel, CompiledFunction, LiteralColumn, Query, Rep, Shape, TableQuery}
import slick.relational.RelationalProfile

object BindColumnExtensionMethodsHelper {
  trait BindColumnExtensionMethodsImpl extends BindColumnExtensionMethods {
    val profile: JdbcProfile

    @deprecated("Use an explicit conversion to an Option column with `.?`", "3.0")
    override implicit def columnToOptionColumn[T: BaseTypedType](c: Rep[T]): Rep[Option[T]] = profile.api.columnToOptionColumn(c)
    override implicit def valueToConstColumn[T: TypedType](v: T): LiteralColumn[T]          = profile.api.valueToConstColumn(v)
    override implicit def columnToOrdered[T: TypedType](c: Rep[T]): ColumnOrdered[T]        = profile.api.columnToOrdered(c)
    override implicit def tableQueryToTableQueryExtensionMethods[T <: RelationalProfile#Table[_], U](
      q: Query[T, U, Seq] with TableQuery[T]
    ): BindColumnExtensionMethods.TableQueryExtensionMethods[T, U] = {
      val tableS = profile.api.tableQueryToTableQueryExtensionMethods(q)
      new BindColumnExtensionMethods.TableQueryExtensionMethods[T, U] {
        override def findBy[P](f: T => Rep[P])(
          implicit ashape: Shape[ColumnsShapeLevel, Rep[P], P, Rep[P]],
          pshape: Shape[ColumnsShapeLevel, P, P, _]
        ): CompiledFunction[Rep[P] => Query[T, U, Seq], Rep[P], P, Query[T, U, Seq], Seq[U]] = tableS.findBy(f)
      }
    }

    /*implicit def streamableCompiledInsertActionExtensionMethods[EU](c: StreamableCompiled[_, _, EU]): InsertActionExtensionMethods[EU] = createInsertActionExtensionMethods[EU](c.compiledInsert.asInstanceOf[CompiledInsert])
  implicit def queryInsertActionExtensionMethods[U, C[_]](q: Query[_, U, C]): InsertActionExtensionMethods[U] = createInsertActionExtensionMethods[U](compileInsert(q.toNode))

  implicit def schemaActionExtensionMethods(sd: SchemaDescription): SchemaActionExtensionMethods = createSchemaActionExtensionMethods(sd)

  implicit def fastPathExtensionMethods[T, P](mp: MappedProjection[T, P]): FastPathExtensionMethods[ResultConverterDomain, T, P] = new FastPathExtensionMethods[ResultConverterDomain, T, P](mp)*/

  }

}
